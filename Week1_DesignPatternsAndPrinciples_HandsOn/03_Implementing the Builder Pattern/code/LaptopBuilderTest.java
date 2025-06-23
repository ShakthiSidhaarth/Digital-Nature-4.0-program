public class LaptopBuilderTest {
    public static void main(String[] args) {
        Laptop officeLaptop = new Laptop.LaptopBuilder("Intel i5", "8GB")
                .operatingSystem("Windows 10")
                .build();

        Laptop gamingLaptop = new Laptop.LaptopBuilder("AMD Ryzen 9", "32GB")
                .touchscreen(true)
                .backlitKeyboard(true)
                .operatingSystem("Windows 11")
                .build();

        Laptop ultraBook = new Laptop.LaptopBuilder("Apple M2", "16GB")
                .touchscreen(true)
                .operatingSystem("macOS Sonoma")
                .build();

        System.out.println("Office Laptop:");
        officeLaptop.showSpecs();

        System.out.println("Gaming Laptop:");
        gamingLaptop.showSpecs();

        System.out.println("Ultrabook:");
        ultraBook.showSpecs();
    }
}
