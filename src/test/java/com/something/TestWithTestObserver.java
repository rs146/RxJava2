package com.something;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import com.something.repository.NamesRepository;

import io.reactivex.Observable;
import io.reactivex.observers.TestObserver;

public class TestWithTestObserver {
	
	@SuppressWarnings("unchecked")
	@Test
	public void testWithTestSubscriber() {
		List<String> expected = Arrays.asList("Max", "Bob", "Rex", "Luther", "Ted", "Mr Red John");
		
		TestObserver<List<String>> testObserver = new TestObserver<>();
		NamesRepository.provideListNames()
			.flatMap(Observable::fromIterable)
			.map(name -> {
				if (name.contains(" ")) {
					return "Mr " + name;
				}
				return name;
			}).toList()
			.subscribe(testObserver);
		
		testObserver.assertResult(expected);
	}
}
