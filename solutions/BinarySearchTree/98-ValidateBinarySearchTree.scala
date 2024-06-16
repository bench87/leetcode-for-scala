//> using scala 3
//> using file ../../structure/BinarySearchTree/TreeNode.scala
//> using dep org.scalameta::munit::1.0.0

import leetcode.structure.TreeNode

object Solution:
  def isValidBST(
      root: TreeNode,
      min: Long = Long.MaxValue,
      max: Long = Long.MinValue
  ): Boolean =
    root == null ||
      (root.value < min && isValidBST(root.left, root.value, max)) &&
      (root.value > max && isValidBST(root.right, min, root.value))

class MyTests extends munit.FunSuite:
  test("98. Validate Binary Search Tree"):
    val testCases = Seq(
      (Array[Integer](5, 1, 4, null, null, 3, 6), false),
      (Array[Integer](2, 1, 3), true),
      (Array[Integer](10, 5, 15, null, null, 6, 20), false),
      (Array[Integer](1, 1), false),
      (Array[Integer](2147483647, 2147483647), false),
      (Array[Integer](2, 1, 3, null, null, null, 4), true),
      (Array[Integer](5, 4, 6, null, null, 3, 7), false),
      (Array[Integer](Integer.MIN_VALUE, null, Integer.MAX_VALUE), true),
      (Array[Integer](Integer.MAX_VALUE, Integer.MAX_VALUE), false),
      (Array[Integer](8, 3, 10, 1, 6, null, 14, null, null, 4, 7, 13), true),
      (Array[Integer](3, 1, 5, 0, 2, 4, 6, null, null, null, 3), false),
      (Array[Integer](5, 2, 8, 1, 3, 6, 9, null, null, null, 4), true)
    )
    testCases.foreach: (input, expected) =>
      val tree   = TreeNode(input)
      val result = Solution.isValidBST(tree)
      assert(
        result == expected,
        s"Test failed for input: ${tree}, expected: $expected, got: $result"
      )
