package homework5.service;

import homework5.collection.UserList;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class MultiThreadCounter implements CounterService{
    @Override
    public int countById(UserList users, int id) {
        int threads = 5;
        ExecutorService executorService = Executors.newFixedThreadPool(threads);
        List<Future<Integer>> futures = new ArrayList<>();
        int chunkSize = (int) Math.ceil((double) users.size());

        for(int i = 0; i < users.size(); i+= chunkSize) {
            int start = i;
            int end = Math.min(i+chunkSize, users.size());
            Callable<Integer> task = () -> {
                int count = 0;
                for (int j = start; j < end; j++) {
                    if (users.get(j).getId() == id) {
                        count++;
                    }
                }
                return count;
            };
            futures.add(executorService.submit(task));
        }
        int total = 0;
            for (Future<Integer> future : futures) {
                try {
                    total += future.get();
                } catch (InterruptedException | ExecutionException e) {
                    System.out.println(e.getMessage());
                }
            }
        executorService.shutdown();
        return total;
    }
}
