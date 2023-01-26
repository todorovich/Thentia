package net.todorovich;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class TreeConstructor
{
    private static final Logger logger = LoggerFactory.getLogger(TreeConstructor.class);
    public static class Pair
    {
        int x;
        int y;

        public Pair(int x, int y)
        {
            this.x = x;
            this.y = y;
        }
    }

    public static class Node {
        int value;
        List<Node> children;

        Node(int value)
        {
            this.value = value;
            children = new ArrayList<>();
        }
    }

    public static Node constructTree(Pair[] edges)
    {
        Multimap<Integer, Integer> map = ArrayListMultimap.create();

        int index = 0;
        for (Pair pair : edges)
        {
            map.put(pair.y, index);
            map.put(pair.x, index);
            index++;
        }

        Node rootNode = new Node(1);

        recurse(null, rootNode, edges, map);

        return rootNode;
    }


    static boolean isPowerOfTwo(int x)
    {
        return (x & (x - 1)) == 0;
    }

    public static void printTree(StringBuilder stringBuilder, Node rootNode, int index)
    {
        if (index == 1)
            stringBuilder.append(rootNode.value).append('\n');

        for (Node child : rootNode.children)
        {
            stringBuilder.append(child.value).append(' ');
        }
        if (isPowerOfTwo(index)) stringBuilder.append('\n');

        int newIndex = index + 1;
        for (Node child : rootNode.children)
        {
            printTree(stringBuilder, child, newIndex);
            newIndex++;
        }

        if (index == 1)
            logger.info(stringBuilder.toString());
    }

    public static void recurse(
        @Nullable Node parentNode, Node currentNode, Pair[] edges, Multimap<Integer, Integer> edgeIndexByValue
    )
    {
        int currentValue = currentNode.value;

        Collection<Integer> indexesOfEdgesContainingParent = edgeIndexByValue.get(currentValue);

        for (Integer index : indexesOfEdgesContainingParent)
        {
            int x = edges[index].x;
            int y = edges[index].y;

            if (x != currentValue && (parentNode == null || x != parentNode.value))
            {
                currentNode.children.add(new Node(edges[index].x));
            }
            else if (y != currentValue && (parentNode == null || y != parentNode.value))
            {
                currentNode.children.add(new Node(edges[index].y));
            }
        }

        for (Node childNode : currentNode.children)
        {
            recurse(currentNode, childNode, edges, edgeIndexByValue);
        }
    }

    public static void main(String[] args)
    {

    }
}