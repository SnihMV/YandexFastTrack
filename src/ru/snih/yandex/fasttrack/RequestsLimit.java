package ru.snih.yandex.fasttrack;

import java.util.*;

public class RequestsLimit {

    public static void main(String[] args) {
        LinkedList<Request> requests = new LinkedList<>();
        Map<Integer, Integer> usersCounter = new HashMap<>();
        Scanner scan = new Scanner(System.in);
        int userLimit = scan.nextInt();
        int serviceLimit = scan.nextInt();
        int duration = scan.nextInt();
        int time;
        while ((time = scan.nextInt()) != -1) {
            Request r = new Request(time, scan.nextInt());

            if (requests.isEmpty()) {
                requests.add(r);
                usersCounter.put(r.getUserId(), 1);
                printResponse("200");
                continue;
            }
            if (time - duration > requests.getLast().getTime()) {
                Integer lastRequestUserId = requests.removeLast().getUserId();
                usersCounter.put(lastRequestUserId, usersCounter.get(lastRequestUserId) - 1);
            }
            if (usersCounter.getOrDefault(r.getUserId(), 0) == userLimit) {
                printResponse("429");
                continue;
            }
            if (requests.size() == serviceLimit) {
                printResponse("503");
                continue;
            }
            requests.addFirst(r);
            usersCounter.put(r.getUserId(), usersCounter.getOrDefault(r.getUserId(), 0) + 1);
            printResponse("200");
        }
    }

    private static void printResponse(String response) {
        System.out.println(response);
        System.out.flush();
    }

    static class Request {
        private final int time, userId;

        public Request(int time, int userId) {
            this.time = time;
            this.userId = userId;
        }

        public int getTime() {
            return time;
        }

        public int getUserId() {
            return userId;
        }
    }
}


