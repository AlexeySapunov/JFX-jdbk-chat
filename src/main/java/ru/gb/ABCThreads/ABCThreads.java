package ru.gb.ABCThreads;

public class ABCThreads {
    static volatile char letter = 'A';
    static final Object monitor = new Object();

    public static void main(String[] args) {
        new Thread(new PrintABC('A', 'B')).start();
        new Thread(new PrintABC('B', 'C')).start();
        new Thread(new PrintABC('C', 'A')).start();
    }

    static class PrintABC implements Runnable {
        private final char currentLetter;
        private final char nextLetter;

        public PrintABC(char currentLetter, char nextLetter) {
            this.currentLetter = currentLetter;
            this.nextLetter = nextLetter;
        }

        @Override
        public void run() {
            for (int i = 0; i < 5; i++) {
                synchronized (monitor) {
                    try {
                        while (letter != currentLetter)
                            monitor.wait();
                        System.out.print(currentLetter);
                        letter = nextLetter;
                        monitor.notifyAll();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
