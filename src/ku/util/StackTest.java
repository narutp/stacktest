package ku.util;

import static org.junit.Assert.*;

import org.junit.Assume;
import org.junit.Before;
import org.junit.Test;

/**
 * Stack test for testing a stack class.
 * 
 * @author Narut Poovorakit
 * 
 * @version 21.04.2017
 *
 */
public class StackTest {

	private Stack stack;

	/**
	 * Create a stack by calling stackFactory to make a stack by given capacity.
	 */
	@Before
	public void setUp() {
		StackFactory.setStackType(0);
		stack = StackFactory.makeStack(2);
	}

	/**
	 * Test stack if it is truly empty or not.
	 */
	@Test
	public void testStackIsEmpty() {
		assertTrue(stack.isEmpty());
		assertFalse(stack.isFull());
		assertEquals(0, stack.size());
	}
	
	/**
	 * Test stack if it's truly full.
	 */
	@Test
	public void testStackIsFull() {
		stack.push(1);
		stack.push(2);
		assertTrue(stack.isFull());
	}
	
	/**
	 * Test the size of the stack from the beginning and after being popped.
	 */
	@Test
	public void testStackSize() {
		assertEquals(2, stack.size());
		stack.pop();
		assertEquals(1, stack.size());
	}
	
	/**
	 * Test when push the value into the stack
	 */
	@Test(expected=java.lang.IllegalStateException.class)
	public void testPushOverCapacity() {
		stack.push(2);
		stack.push(1);
		stack.push(2);
		stack.push(1);
		stack.push(2);
		stack.push(1);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testPushNull() {
		stack.push(null);
	}
	
	@Test
	public void testNormalPeekStack() {
		stack.push("Elephant");
		stack.push("Banana");
		assertEquals("Banana", stack.peek());
	}
	
	@Test
	public void testHardPeekStack() {
		stack.push("Ant");
		stack.push("Bee");
		assertEquals("Bee", stack.peek());
		assertEquals("Bee", stack.peek());
		assertFalse("Ant", stack.peek().equals("Ant"));
	}
	
	@Test
	public void testNormalPopStack() {
		stack.push("Ant");
		assertEquals("Ant", stack.pop());
	}
	
	@Test
	public void testHardPopStack() {
		stack.push("Ant");
		stack.push("Bee");
		assertEquals("Bee",stack.pop());
		stack.push("New Bee");
		assertEquals("New Bee",stack.pop());
		stack.pop();
		stack.peek();
		assertEquals(0, stack.size());
	}
	
	@Test(expected=java.util.EmptyStackException.class)
	public void testPopEmptyStack() {
		Assume.assumeTrue(stack.isEmpty());
		stack.pop();
	}
	
	@Test
	public void testPeekEmptyStack() {
		Assume.assumeTrue(stack.isEmpty());
		assertEquals(null, stack.peek());
	}

	private Stack makeStack(int capacity) {
		// a dummy stack
		return new DummyStack(capacity);
	}
}
