package ru.snih.yandex.fasttrack;

import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        StackWithMax stack = new StackWithMax();
        Scanner sc = new Scanner(System.in);
        int q = Integer.parseInt(sc.nextLine());
        for (int i = 0; i < q; i++) {
            String[] request = sc.nextLine().split(" ");
            switch (request[0]) {
                case "push" -> stack.push(Integer.parseInt(request[1]));
                case "pop" -> stack.pop();
                case "max" -> System.out.println(stack.max());
                default -> throw new RuntimeException("Unknown command");
            }
        }
    }
}

public class StackWithMax {

    private Node head;

    public void push(int value) {
        Node newHead;
        if (head != null) {
            newHead = new Node(value, Math.max(value, head.currentMax));
        } else newHead = new Node(value, value);
        newHead.next = head;
        head = newHead;
    }

    public int pop() {
        if (head != null) {
            int res = head.value;
            head = head.next;
            return res;
        }
        throw new RuntimeException("Stack is empty");
    }

    public int max() {
        if (head != null)
            return head.currentMax;
        throw new RuntimeException("Stack is empty");
    }

    private static class Node {
        private int value;
        private int currentMax;
        private Node next;

        public Node(int value, int max) {
            this.value = value;
            this.currentMax = max;
        }
    }
}

