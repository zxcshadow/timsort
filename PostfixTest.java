package linked;

import org.junit.Test;

import static org.junit.Assert.*;

public class PostfixTest {

    @Test
    public void infixToPostfix() {
        Postfix post = new Postfix();
        assertEquals("1sin", post.infixToPostfix("sin (1)"));
    }
    @Test
    public void infixToPostfixA() {
        Postfix post = new Postfix();
        assertEquals("3×12+3+4+5+4/123*5453/+135*+cos+sin+", post.infixToPostfix("3×(1+2+3+4+5)/4*123+sin(54+5/3+cos(1+3*5))"));
    }

}