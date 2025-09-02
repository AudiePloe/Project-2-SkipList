
class Node {
    User user;
    Node[] forward; // Array of pointers for different levels

    public Node(User user, int level)
    {
        this.user = user;
        this.forward = new Node[level + 1];
    }


    @Override public String toString()
    {
        return (user.SSN + " " + user.FirstName + " " + user.LastName);
    }
}
