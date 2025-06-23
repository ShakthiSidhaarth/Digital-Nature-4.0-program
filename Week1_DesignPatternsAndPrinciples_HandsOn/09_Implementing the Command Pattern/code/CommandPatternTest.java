public class CommandPatternTest {
    public static void main(String[] args) {
        Light bedroomLight = new Light("Bedroom");
        Light kitchenLight = new Light("Kitchen");

        Command lightOn = new LightOnCommand(bedroomLight);
        Command lightOff = new LightOffCommand(bedroomLight);

        RemoteControl remote = new RemoteControl();

        remote.setCommand(lightOn);
        remote.pressButton();

        remote.setCommand(lightOff);
        remote.pressButton();

        remote.setCommand(new LightOnCommand(kitchenLight));
        remote.pressButton();

        remote.setCommand(new LightOffCommand(kitchenLight));
        remote.pressButton();
    }
}
