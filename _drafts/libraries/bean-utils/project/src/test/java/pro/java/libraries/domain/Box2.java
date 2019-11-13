package pro.java.libraries.domain;

public class Box2 {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isName() {
        return "Name".equalsIgnoreCase(name);
    }
}