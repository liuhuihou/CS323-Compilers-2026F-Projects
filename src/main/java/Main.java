//示例代码
// import generated.Calc1.Calc1Lexer;
// import org.antlr.v4.runtime.CharStream;
// import org.antlr.v4.runtime.CharStreams;
// import org.antlr.v4.runtime.CommonTokenStream;
// import org.antlr.v4.runtime.Token;
// import org.antlr.v4.runtime.Vocabulary;
// import java.util.List;


// public class Main {
//     public static void main(String[] args) throws Exception{
//         // 从字符串获取字符流。若要从文件读取，可使用 CharStreams.fromFileName("文件路径")
//         CharStream charStream = CharStreams.fromFileName("testcases/test1.calc");
//         Calc1Lexer lexer = new Calc1Lexer(charStream);

//         // 基于词法分析器实例，创建 Token 流
//         CommonTokenStream tokens = new CommonTokenStream(lexer);

//         // 调用 fill() 方法，让词法分析器开始工作，填充 Token 流
//         tokens.fill();
//         Vocabulary vocabulary = lexer.getVocabulary();

//         // 打印 Token 流信息
//         for (Token token : tokens.getTokens()) {
//             System.out.printf("Line:%d:%d Token: %s %d\n" ,
//             token.getLine(),
//             token.getCharPositionInLine(),
//             vocabulary.getSymbolicName(token.getType()),
//             token.getTokenIndex()
//             );
//         }
//     }
// }

// import generated.IPV4.IPV4Lexer;
// import org.antlr.v4.runtime.CharStream;
// import org.antlr.v4.runtime.CharStreams;
// import org.antlr.v4.runtime.CommonTokenStream;
// import org.antlr.v4.runtime.Token;

// public class Main {
//     public static void main(String[] args) {
//         CharStream charStream =
//                 CharStreams.fromString("257.016.299.233");

//         IPV4Lexer lexer = new IPV4Lexer(charStream);

//         CommonTokenStream tokens = new CommonTokenStream(lexer);

//         tokens.fill();

//         for (Token token : tokens.getTokens()) {
//             // 不输出 EOF
//             if (token.getType() == Token.EOF) {
//                 continue;
//             }

//             String tokenType =lexer.getVocabulary().getSymbolicName(token.getType());

//             System.out.printf(
//                     "TokenType: %s, Lexeme: %s%n",
//                     tokenType,
//                     token.getText()
//             );
//         }
//     }
// }

//字符串匹配
import generated.Calc1.*;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.Vocabulary;
import org.antlr.v4.runtime.tree.ParseTree;
import java.util.List;


public class Main {
    public static void main(String[] args) throws Exception{
        // 从字符串获取字符流。若要从文件读取，可使用 CharStreams.fromFileName("文件路径")
        CharStream charStream = CharStreams.fromFileName("testcases/test1.calc");
        Calc1Lexer lexer = new Calc1Lexer(charStream);

        // 基于词法分析器实例，创建 Token 流
        CommonTokenStream tokens = new CommonTokenStream(lexer);

        // 调用 fill() 方法，让词法分析器开始工作，填充 Token 流
        tokens.fill();
        Vocabulary vocabulary = lexer.getVocabulary();

        Calc1Parser parser = new Calc1Parser(tokens);

        // 开始语法分析，调用起始规则 expr
        ParseTree tree = parser.root();

        System.out.println("Parse Tree: " + tree.toStringTree(parser)); // 打印语法分析树

        // 打印 Token 流信息
        for (Token token : tokens.getTokens()) {
            System.out.printf("Line:%d:%d TokenType: %s, Text: %s, Index: %d%n",
            token.getLine(),
            token.getCharPositionInLine(),
            vocabulary.getSymbolicName(token.getType()),
            token.getText(),
            token.getTokenIndex()
            );
        }
    }
}