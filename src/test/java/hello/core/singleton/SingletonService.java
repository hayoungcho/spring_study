package hello.core.singleton;

public class SingletonService {

    private static final SingletonService instance = new SingletonService();

    //항상 같은 instance를 반환한다.
    public static SingletonService getInstance(){
        return instance;
    }
    private SingletonService() {

    }

    private void logic(){
        System.out.println("싱글톤 객체 로직 호출");
    }
    public static void main(String[] args) {
    }
}
