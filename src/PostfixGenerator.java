import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class PostfixGenerator {
	public BigDecimal evaluate(List<Object> expressions) {
        Stack stack = new Stack();
        
        List<Object> postfix = convertInfixToPostfix(expressions);
        for(Object data : postfix) {
            if(isNumeric(data.toString())) {
                stack.push(data.toString());
            } else if(isOperator(data.toString())) {
                BigDecimal operand1 = new BigDecimal(stack.pop().toString());
                BigDecimal operand2 = new BigDecimal(stack.pop().toString());
                
                BigDecimal result = new BigDecimal(0);
                if(data.toString().equals("+")) {
                    result = operand2.add(operand1);
                    stack.push(result);
                } else if(data.toString().equals("-")) {
                    result = operand2.subtract(operand1);
                    stack.push(result);
                } else if(data.toString().equals("*")) {
                    result = operand2.multiply(operand1);
                    stack.push(result);
                } else if(data.toString().equals("/")) {
                    result = operand2.divide(operand1, 2, BigDecimal.ROUND_HALF_UP);
                    stack.push(result);
                }
            }
        }
        return (BigDecimal) stack.pop();
    }
    
    protected List<Object> convertInfixToPostfix(List<Object> expressions) {
        Stack stack = new Stack();
        List<Object> postfix = new ArrayList<>();
        
        if(!expressions.isEmpty() && expressions.get(0).toString().equals("-")) {
            expressions.set(1, new BigDecimal(expressions.get(1).toString()).multiply(new BigDecimal(-1)));
        }
        
        for(int i = 0;i <expressions.size();i++) {
            String expression = expressions.get(i).toString();
            
            if(i > 0) {
                if(expression.equals("(")) {
                    stack.push(expression);
                } else if(expression.equals(")")) {
                    while(!stack.peek().toString().equals("(")) {
                        postfix.add(stack.pop().toString());
                    }
                    stack.pop();
                } else if(isOperator(expression)) {
                    while(!stack.isEmpty() && priority(stack.peek().toString()) >= priority(expression)) {
                        postfix.add(stack.pop().toString());
                    }
                    stack.push(expression);
                } else {
                    postfix.add(expression);
                }
            }
        }
        while(!stack.isEmpty()) {
            String data = stack.pop().toString();
            if(!data.equals("(")) {
                postfix.add(data);
            }
        }
        return postfix;
    }
    
    protected int priority(String operator) {
        if(operator.equals("(")) {
            return 0;
        }
        if(operator.equals("+") || operator.equals("-")) {
            return 1;
        } 
        if(operator.equals("*") || operator.equals("/")) {
            return 2;
        } else {
            return -1;
        }
    }
    
    protected boolean isOperator(String data) {
        if(data.equals("+") || data.equals("-") || data.equals("*") || data.equals("/")) {
            return true;
        }
        return false;
    }
    
    protected boolean isNumeric(String str) { 
      try {  
        Double.parseDouble(str);  
        return true;
      } catch(NumberFormatException e){  
        return false;  
      }  
    }
}
