import java.util.*;

class Passenger {
    String name;
    int age;
    String gender;

    Passenger(String name, int age, String gender) {
        this.name = name;
        this.age = age;
        this.gender = gender;
    }

    @Override
    public String toString() {
        return name + " (" + age + ", " + gender + ")";
    }
}

public class RailwayWaitingList {
    private static final int MAX_CONFIRMED_TICKETS = 3;  // limit for confirmed tickets
    private List<Passenger> confirmedList = new ArrayList<>();
    private Queue<Passenger> waitingList = new LinkedList<>();

    public void bookTicket(String name, int age, String gender) {
        Passenger p = new Passenger(name, age, gender);
        if (confirmedList.size() < MAX_CONFIRMED_TICKETS) {
            confirmedList.add(p);
            System.out.println("✅ Ticket confirmed for " + name);
        } else {
            waitingList.add(p);
            System.out.println("⏳ Added to waiting list: " + name);
        }
    }

    public void cancelTicket(String name) {
        boolean removed = false;
        Iterator<Passenger> iterator = confirmedList.iterator();
        while (iterator.hasNext()) {
            Passenger p = iterator.next();
            if (p.name.equalsIgnoreCase(name)) {
                iterator.remove();
                removed = true;
                System.out.println("❌ Ticket cancelled for " + name);
                break;
            }
        }

        if (removed && !waitingList.isEmpty()) {
            Passenger next = waitingList.poll();  // remove from queue
            confirmedList.add(next);
            System.out.println("🎟️ Waiting list passenger moved to confirmed: " + next.name);
        } else if (!removed) {
            System.out.println("⚠️ Passenger not found in confirmed list.");
        }
    }

    public void showStatus() {
        System.out.println("\n--- Confirmed Tickets ---");
        for (Passenger p : confirmedList) {
            System.out.println(p);
        }

        System.out.println("\n--- Waiting List ---");
        for (Passenger p : waitingList) {
            System.out.println(p);
        }
        System.out.println("---------------------------\n");
    }

    public static void main(String[] args) {
        RailwayWaitingList system = new RailwayWaitingList();
        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n===== Railway Ticket System =====");
            System.out.println("1. Book Ticket");
            System.out.println("2. Cancel Ticket");
            System.out.println("3. Show Status");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter age: ");
                    int age = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter gender: ");
                    String gender = sc.nextLine();
                    system.bookTicket(name, age, gender);
                    break;

                case 2:
                    System.out.print("Enter passenger name to cancel: ");
                    String cname = sc.nextLine();
                    system.cancelTicket(cname);
                    break;

                case 3:
                    system.showStatus();
                    break;

                case 4:
                    System.out.println("🚉 Thank you for using the Railway System!");
                    break;

                default:
                    System.out.println("Invalid choice. Try again.");
            }
        } while (choice != 4);

        sc.close();
    }
}
