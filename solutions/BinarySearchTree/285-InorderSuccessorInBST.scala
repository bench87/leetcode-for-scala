//> using scala 3
//> using file ../../structure/BinarySearchTree/TreeNode.scala
//> using dep org.scalameta::munit::1.0.0

import leetcode.structure.TreeNode

object InorderSuccessorInBSTSolution:
  def inorderSuccessor(root: TreeNode, p: TreeNode): TreeNode =
    var prev: TreeNode = null
    var curr = root
    while (curr != null) {
      if curr.value > p.value then
        prev = curr
        curr = curr.left
      else curr = curr.right
    }
    return prev

class InorderSuccessorInBSTSuite extends munit.FunSuite:
  test("285. Inorder Successor in BST"):

    val testCases = Seq(
      (Array[Integer](5, 1, 4, null, null, 3, 6), 4, 5),
      (Array[Integer](2, 1, 3), 1, 2),
      (Array[Integer](10, 5, 15, null, null, 6, 20), 6, 10),
      (Array[Integer](10, 5, 15, null, null, 6, 20), 10, 15),
      (Array[Integer](5, 3, 6, 2, 4, null, null, 1), 3, 4),
      (Array[Integer](5, 3, 6, 2, 4, null, null, 1), 4, 5),
      (Array[Integer](10, 5, 15, 2, 8, 12, 20, null, null, 6, 9), 9, 10),
      (Array[Integer](10, 5, 15, 2, 8, 12, 20, null, null, 6, 9), 5, 6),
      (Array[Integer](2, 1), 2, null),
      (Array[Integer](2, 1), 1, 2)
    )

    for ((arr, pValue, expected) <- testCases) {
      val root = TreeNode(arr)
      val p = findNode(root, pValue)
      val result = InorderSuccessorInBSTSolution.inorderSuccessor(root, p)
      val resultValue = if (result != null) result.value else null
      assertEquals(resultValue, expected)
    }

    def findNode(root: TreeNode, value: Int): TreeNode =
      if root == null || root.value == value then root
      else
        val left = findNode(root.left, value)
        if left != null then left else findNode(root.right, value)
