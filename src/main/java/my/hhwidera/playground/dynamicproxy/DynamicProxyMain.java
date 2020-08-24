package my.hhwidera.playground.dynamicproxy;

class DynamicProxyMain {

    public static void main(String[] args) {
        // slow worker
        Worker worker = new BoxWorker();
        worker.doWork();

        // with proxy
        worker = TimingProxy.bind(worker);
        worker.doWork();
    }

}
