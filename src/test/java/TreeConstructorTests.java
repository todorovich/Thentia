import net.todorovich.TreeConstructor;
import org.junit.jupiter.api.Test;

public class TreeConstructorTests
{
    @Test
    public void Example1()
    {
        TreeConstructor.Pair[] edges = new TreeConstructor.Pair[]{
             new TreeConstructor.Pair(4, 5),
             new TreeConstructor.Pair(5, 3),
             new TreeConstructor.Pair(1, 5),
             new TreeConstructor.Pair(2, 1)
        };

        TreeConstructor.Node rootNode = TreeConstructor.constructTree(edges);

        TreeConstructor.printTree(new StringBuilder(), rootNode, 1);
    }

    @Test
    public void Example2()
    {
        TreeConstructor.Pair[] edges = new TreeConstructor.Pair[]{
            new TreeConstructor.Pair(4, 5),
            new TreeConstructor.Pair(5, 3),
            new TreeConstructor.Pair(1, 5),
            new TreeConstructor.Pair(2, 5)
        };

        TreeConstructor.Node rootNode = TreeConstructor.constructTree(edges);


        TreeConstructor.printTree(new StringBuilder(), rootNode, 1);
    }
}
