package ku.util;

import static org.junit.Assert.*;

import org.junit.Assume;
import org.junit.Before;
import org.junit.Test;

public class StackTest {

	private Stack stack;

	@Before
	public void setUp() {
		StackFactory.setStackType(1);
		stack = StackFactory.makeStack(2);
	}

	@Test
	public void testStackIsEmpty() {
		assertTrue(stack.isEmpty());
		assertFalse(stack.isFull());
		assertEquals(0, stack.size());
	}
	
	@Test
	public void testStackIsFull() {
		stack.push(1);
		stack.push(2);
		assertTrue(stack.isFull());
	}
	
	@Test
	public void testStackSize() {
		assertEquals(2, stack.size());
		stack.pop();
		assertEquals(1, stack.size());
	}
	
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

	private Stack makeStack(int capacity) {
		// a dummy stack
		return new DummyStack(capacity);
	}
}
