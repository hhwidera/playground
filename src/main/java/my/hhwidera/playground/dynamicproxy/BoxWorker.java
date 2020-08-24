package my.hhwidera.playground.dynamicproxy;

class BoxWorker implements Worker {

    public void doWork() {
        System.out.println("start working...");
        try {
            Thread.sleep(2_000L);
        } catch (InterruptedException ignore) {
        }
        System.out.println("... done.");
    }

}
