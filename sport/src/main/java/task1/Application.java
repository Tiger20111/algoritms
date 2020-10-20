package task1;

import task1.tree.SuffixTree;

import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Application {
    public static void main(String[] args) throws IOException {
        File file = new File("/Users/tiger/IdeaProjects/algoritms/sport/src/main/java/task1/input.txt");
        FileReader fr = new FileReader(file);
        BufferedReader reader = new BufferedReader(fr);
        String AStr = reader.readLine();
        String BStr = reader.readLine();
        AStr = AStr.substring(1, AStr.length() - 1);
        BStr = BStr.substring(1, BStr.length() - 1);

        SolveTask(AStr, BStr);
    }

    private static void SolveTask(String AStr, String BStr) {

        SuffixTree suffixTree = new SuffixTree();
        String str = suffixTree.lsc(AStr.replaceAll(",", " "), BStr.replaceAll(",", " "));
        List<Integer> pattern =Arrays.stream(str.split("\\s"))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
        System.out.println(pattern.size());
    }

}
