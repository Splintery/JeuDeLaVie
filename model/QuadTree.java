package model;

import java.awt.Rectangle;
import java.util.LinkedList;
import java.util.Iterator;

public class QuadTree implements Iterable<Cellule> {

	private Rectangle boundary;
	private int capacity;
	private LinkedList<Cellule> points;

	private QuadTree northEast;
	private QuadTree southEast;
	private QuadTree northWest;
	private QuadTree southWest;
	private boolean isSubdivided = false;
	private String treeName;


	private QuadTree parent;

	public QuadTree(Rectangle boundary, int capacity, QuadTree parent, String treeName) {
		this.boundary = boundary;
		this.capacity = capacity;
		this.parent = parent;
		this.points = new LinkedList<>();
		this.treeName = treeName;
	}

	public QuadTree(Rectangle boundary, int capacity) {
		this(boundary, capacity, null, "root");
	}

	public boolean add(Cellule c) {
		if (boundary.contains(c.getX(), c.getY())) {
			if (points.size() < capacity && isSubdivided == false) {
				return points.add(c);
			} else {
				if (isSubdivided == false) {
					subdivide();
					distribute();
				}
				if (northEast.add(c)) {
					return true;
				}
				if (southEast.add(c)) {
					return true;
				}
				if (southWest.add(c)) {
					return true;
				}
				if (northWest.add(c)) {
					return true;
				}
			}
		}
		return false;
	}
	private void subdivide() {
		double x = boundary.getX();
		double y = boundary.getY();
		int w = boundary.width;
		int h = boundary.height;
		if (isSubdivided == false) {
			northEast = new QuadTree(new Rectangle((int)((w/2) + x), (int)y, (int)Math.ceil((double)w/2), (int)Math.ceil((double)h/2)), capacity, this, "NE");
			southEast = new QuadTree(new Rectangle((int)((w/2) + x), (int)((h/2) + y), (int)Math.ceil((double)w/2), (int)Math.ceil((double)h/2)), capacity, this, "SE");
			northWest = new QuadTree(new Rectangle((int)x, (int)y, (int)Math.ceil((double)w /2), (int)Math.ceil((double)h/2)), capacity, this, "NW");
			southWest = new QuadTree(new Rectangle((int)x, (int)((h/2) + y), (int)Math.ceil((double)w /2), (int)Math.ceil((double)h/2)), capacity, this, "SW");
			isSubdivided = true;
		}
	}
	private void distribute() {
		if (isSubdivided) {
			for (Cellule c : points) {
				add(c);
			}
			points.clear();
		}
	}
	public boolean contains(Cellule c) {
		if (boundary.contains(c.getX(), c.getY())) {
			if (isSubdivided == false) {
				return points.contains(c);
			} else {
				if (northEast.contains(c)) {
					return true;
				}
				if (southEast.contains(c)) {
					return true;
				}
				if (southWest.contains(c)) {
					return true;
				}
				if (northWest.contains(c)) {
					return true;
				}
			}
		}
		return false;
	}
	public LinkedList<Cellule> containsAll(Rectangle field) {
		if (boundary.intersects(field)) {
			LinkedList<Cellule> pointsFound = new LinkedList<>();
			return containsAllAux(field, pointsFound);
		}
		return null;
	}
	private LinkedList<Cellule> containsAllAux(Rectangle field, LinkedList<Cellule> pointsFound) {
		if (isSubdivided == false) {
			for (Cellule c : points) {
				if (field.contains(c.getX(), c.getY())) {
					pointsFound.add(c);
				}
			}
		} else {
			northEast.containsAllAux(field, pointsFound);
			southEast.containsAllAux(field, pointsFound);
			northWest.containsAllAux(field, pointsFound);
			southWest.containsAllAux(field, pointsFound);
		}
		return pointsFound;
	}
	public boolean remove(Cellule c) {
		if (boundary.contains(c.getX(), c.getY())) {
			if (isSubdivided == false) {
				return points.remove(c);
			} else {
				boolean removed = false;
				if (northEast.remove(c) && removed == false) {
					removed = true;
				}
				if (southEast.remove(c) && removed == false) {
					removed = true;
				}
				if (southWest.remove(c) && removed == false) {
					removed = true;
				}
				if (northWest.remove(c) && removed == false) {
					removed = true;
				}
				if (canMerge()) {
					this.isSubdivided = false;
					northEast.merge();
					southEast.merge();
					northWest.merge();
					southWest.merge();
					this.isSubdivided = true;
					unSubdivide();
				} else if (childEmpty()) {
					unSubdivide();
				}
			}
		}
		return false;
	}
	private boolean canMerge() {
		if (isSubdivided) {
			return northEast.points.size() + southEast.points.size() + northWest.points.size() + southWest.points.size() <= capacity;
		}
		return false;
	}
	private void merge() {
		for (Cellule c : points) {
			parent.add(c);
		}
	}
	private boolean childEmpty() {
		if (isSubdivided) {
			return northEast.points.isEmpty() && southEast.points.isEmpty() && northWest.points.isEmpty() && southWest.points.isEmpty();
		}
		return true;
	}
	private void unSubdivide() {
		if (isSubdivided) {
			isSubdivided = false;
			northEast = null;
			southEast = null;
			northWest = null;
			southWest = null;
		}
	}

	public Iterator<Cellule> iterator() {
		return new QuadTreeIterator(this);
	}
	private QuadTree nextTree() {
		switch (treeName) {
		case "root" :
			return northWest;
		case "NW" :
			return parent.northEast;
		case "NE" :
			return parent.southWest;
		case "SW" :
			return parent.southEast;
		case "SE" :
			if (parent.treeName.equals("root") == false) {
				return parent.nextTree();
			}
		}
		return null;
	}

	private class QuadTreeIterator implements Iterator<Cellule> {

		public QuadTree currentTree;
		public Cellule currentCell;
		public int index = 0;

		public QuadTree hasNextTree;
		public int hasNextIndex;

		public QuadTreeIterator(QuadTree
		 root) {
			currentTree = root;
		}
		public Cellule next() {
			if (currentTree == null)	return null;

			if (currentTree.isSubdivided == false && index < currentTree.points.size()) {
				index++;
				return currentTree.points.get(index-1);
			} else {
				index = 0;
				if (currentTree.isSubdivided) {
					currentTree = currentTree.northWest;
				} else {
					currentTree = currentTree.nextTree();
				}
				return next();
			}
		}
		
		public boolean hasNext() {
			hasNextTree = currentTree;
			hasNextIndex = index;
			return hasNextAux();
		}
		private boolean hasNextAux() {
			if (hasNextTree != null) {
				if (hasNextTree.isSubdivided == false && hasNextIndex < hasNextTree.points.size()) {
					return true;
				} else {
					hasNextIndex = 0;
					if (hasNextTree.isSubdivided) {
						hasNextTree = hasNextTree.northWest;
					} else {
						hasNextTree = hasNextTree.nextTree();
					}
					return hasNextAux();
				}
			}
			return false;
		}
	}
	@Override
	public String toString() {
		String res = "";

		res += "[\n";
		for (Cellule c : points) {
			res += "\t" + c + "\n";
		}
		res += "]\n";
		if (isSubdivided) {
			res += "Fils {\n";
			res += "northWest\n" + northWest.toString();
			res += "northEast\n" + northEast.toString();
			res += "southWest\n" + southWest.toString();
			res += "southEast\n" + southEast.toString(); 
			res += "}\n";
		}
		return res;
	}

	public static void main(String[] args) {

		/*Petit QuadTree pour test l'itérateur*/
		QuadTree tree = new QuadTree(new Rectangle(0, 0, 100, 100), 1);

		Cellule a = new Cellule(25, 25);
		Cellule b = new Cellule(75, 25);
		Cellule c = new Cellule(75, 75);
		Cellule d = new Cellule(25, 75);
		Cellule e = new Cellule(49, 49);
		Cellule f = new Cellule(90, 90);
		tree.add(a);
		tree.add(b);
		tree.add(c);
		tree.add(d);
		tree.add(e);
		tree.add(f);

		/*Petit Exemple de comment on créer un Iterateur et comment l'utiliser*/
		Iterator<Cellule> itr = tree.iterator();
		
		while (itr.hasNext()) {
			System.out.println(itr.next());
		}
		/*Lorsque l'iterateur à atteint la fin de l'arbre, l'appel à "next()" renvoi null*/
	}
}