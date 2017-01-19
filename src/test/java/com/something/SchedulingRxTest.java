package com.something;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

import io.reactivex.Observable;
import io.reactivex.schedulers.TestScheduler;

public class SchedulingRxTest {

	@Test
	public void testWithScheduler() {
		TestScheduler scheduler = new TestScheduler();
		List<Long> expected = Arrays.asList(0L, 1L, 2L, 3L, 4L);
		List<Long> result = new ArrayList<>();

		Observable.interval(1, TimeUnit.SECONDS, scheduler).take(5).subscribe(i -> result.add(i));

		assertTrue(result.isEmpty());
		scheduler.advanceTimeBy(5, TimeUnit.SECONDS);
		assertTrue(result.equals(expected));
	}
}
