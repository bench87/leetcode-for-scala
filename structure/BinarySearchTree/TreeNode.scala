package leetcode.structure
import scala.collection.mutable.{Queue, ListBuffer}

case class TreeNode(
    var value: Int,
    var left: TreeNode = null,
    var right: TreeNode = null
):
  override def toString: String =
    val queue  = Queue[Option[TreeNode]]()
    val result = ListBuffer[String]()

    queue.enqueue(Some(this))
    while queue.nonEmpty do
      queue.dequeue() match
        case Some(node) =>
          result.append(node.value.toString)
          queue.enqueue(Option(node.left))
          queue.enqueue(Option(node.right))
        case None =>
          result.append("null")
    while result.nonEmpty && result.last == "null" do
      result.remove(result.size - 1)

    result.mkString("[", ",", "]")

object TreeNode:
  def apply(arr: Array[Integer]): TreeNode =
    if arr.isEmpty then return null
    def createNode(i: Int): TreeNode =
      if i >= arr.length || arr(i) == null then null
      else TreeNode(arr(i), createNode(2 * i + 1), createNode(2 * i + 2))
    createNode(0)
