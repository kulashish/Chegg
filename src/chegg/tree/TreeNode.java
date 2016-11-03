package chegg.tree;

public class TreeNode {

	private String name;
	private int year;
	private TreeNode parent;

	public TreeNode getParent() {
		return parent;
	}

	public void setParent(TreeNode parent) {
		this.parent = parent;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public static TreeNode parseLine(String line) {
		TreeNode node = new TreeNode();
		String[] splits = line.split("\\s+");
		node.setName(splits[0]);
		node.setYear(Integer.parseInt(splits[1]));
		return node;
	}

	@Override
	public boolean equals(Object obj) {
		TreeNode node = (TreeNode) obj;
		return this.name.equalsIgnoreCase(node.name) && this.year == node.year;
	}

	@Override
	public String toString() {
		return "Name: " + name + " Year: " + year;
	}

}
