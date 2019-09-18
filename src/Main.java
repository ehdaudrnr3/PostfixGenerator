import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

public class Main {

	public static void main(String[] args) {
		List<Object> expressions = Arrays.asList(new Object[] {
            "+", new BigDecimal(152), "+", new BigDecimal(8), "/" ,new BigDecimal(3), "-", "(", new BigDecimal(5), "+", new BigDecimal(8), ")", "*", new BigDecimal(20), "-", new BigDecimal(5)
        });
        
        PostfixGenerator generator = new PostfixGenerator();
        System.out.println(generator.evaluate(expressions));

	}

}
