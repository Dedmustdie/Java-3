package Core.Habr;

class Completed implements ParserWorker.OnCompleted {

    @Override
    public void OnCompleted(Object sender) {
        System.out.println("Загрузка закончена");
    }
}