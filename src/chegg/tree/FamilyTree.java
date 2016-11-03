package chegg.tree;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class FamilyTree {

	private TreeNode root;
	private List<TreeNode> nodesList;
	private BufferedReader reader;

	public FamilyTree(String path) throws IOException {
		reader = new BufferedReader(new FileReader(path));
		nodesList = new ArrayList<TreeNode>();
		buildTree(null);
	}

	public TreeNode getRoot() {
		return root;
	}

	public void buildTree(TreeNode parent) throws IOException {
		String line = reader.readLine();
		TreeNode newnode = null;
		if (line != null) {
			newnode = TreeNode.parseLine(line);
			newnode.setParent(parent);
			nodesList.add(newnode);
		}
		if (root == null && newnode != null)
			root = newnode;
		if ((line = reader.readLine()) != null) {
			for (int i = 0; i < Integer.parseInt(line); i++)
				buildTree(newnode);
		}
	}

	public void preorder(TreeNode parent) {
		System.out.println(parent.toString());
		for (TreeNode child : children(parent))
			preorder(child);
	}

	public void postorder(TreeNode parent) {
		for (TreeNode child : children(parent))
			postorder(child);
		System.out.println(parent);
	}

	public void breadthfirst() {
		Queue<TreeNode> queue = new LinkedList<TreeNode>();
		queue.add(root);
		TreeNode currentNode = null;
		while ((currentNode = queue.poll()) != null) {
			System.out.println(currentNode);
			queue.addAll(children(currentNode));
		}
	}

	public List<TreeNode> children(TreeNode parent) {
		List<TreeNode> children = new ArrayList<TreeNode>();
		for (TreeNode node : nodesList)
			if (node.getParent() != null && node.getParent().equals(parent))
				children.add(node);
		return children;
	}

	private List<TreeNode> findNodes(String name) {
		List<TreeNode> nodes = new ArrayList<TreeNode>();
		for (TreeNode node : nodesList)
			if (node.getName().equalsIgnoreCase(name))
				nodes.add(node);
		return nodes;
	}

	public void findCommonAncestors() throws IOException {
		String line = reader.readLine();
		int num = 0;
		if (line != null)
			num = Integer.parseInt(line);
		for (int i = 0; i < num; i++) {
			String[] splits = reader.readLine().split("\\s+");
			common(splits[0], splits[1]);
		}
	}

	public void common(String name1, String name2) {
		List<TreeNode> namesList1 = findNodes(name1);
		List<TreeNode> namesList2 = findNodes(name2);
		for (TreeNode node1 : namesList1)
			for (TreeNode node2 : namesList2)
				common(node1, node2);
	}

	private void common(TreeNode node1, TreeNode node2) {
		System.out.println("Common ancestors of (" + node1 + ") and (" + node2 + ")");
		List<TreeNode> ancestors1 = ancestors(node1);
		List<TreeNode> ancestors2 = ancestors(node2);
		ancestors1.retainAll(ancestors2);
		for (TreeNode node : ancestors1)
			System.out.println(node);
	}

	private List<TreeNode> ancestors(TreeNode node1) {
		List<TreeNode> ancestors = new ArrayList<TreeNode>();
		while (node1 != null) {
			ancestors.add(node1);
			node1 = node1.getParent();
		}
		return ancestors;
	}

	public static void main(String[] args) {
		try {
			FamilyTree tree = new FamilyTree("data/family_tree.txt");
			System.out.println("Printing family tree in preorder");
			tree.preorder(tree.getRoot());
			System.out.println("Printing family tree in postorder");
			tree.postorder(tree.getRoot());
			System.out.println("Printing family tree in breadth first");
			tree.breadthfirst();
			tree.findCommonAncestors();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
