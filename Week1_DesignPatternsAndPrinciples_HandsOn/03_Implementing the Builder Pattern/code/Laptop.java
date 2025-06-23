public class Laptop {
    private String processor;
    private String memory;
    public  boolean hasTouchscreen;
    private boolean hasBacklitKeyboard;
    private String os;

    private Laptop(LaptopBuilder builder) {
        this.processor = builder.processor;
        this.memory = builder.memory;
        this.hasTouchscreen = builder.hasTouchscreen;
        this.hasBacklitKeyboard = builder.hasBacklitKeyboard;
        this.os = builder.os;
    }

    public void showSpecs() {
        System.out.println("Processor: " + processor);
        System.out.println("Memory: " + memory);
        System.out.println("Touchscreen: " + (hasTouchscreen ? "Yes" : "No"));
        System.out.println("Backlit Keyboard: " + (hasBacklitKeyboard ? "Yes" : "No"));
        System.out.println("Operating System: " + os);
        System.out.println("----------------------------");
    }

    public static class LaptopBuilder {
        private String processor;
        private String memory;
        private boolean hasTouchscreen;
        private boolean hasBacklitKeyboard;
        private String os;

        public LaptopBuilder(String processor, String memory) {
            this.processor = processor;
            this.memory = memory;
        }

        public LaptopBuilder touchscreen(boolean val) {
            this.hasTouchscreen = val;
            return this;
        }

        public LaptopBuilder backlitKeyboard(boolean val) {
            this.hasBacklitKeyboard = val;
            return this;
        }

        public LaptopBuilder operatingSystem(String os) {
            this.os = os;
            return this;
        }

        public Laptop build() {
            return new Laptop(this);
        }
    }
}
