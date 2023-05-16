import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Menu {
    public static void menuClientes(ArrayList<Client> data, ArrayList<Plan> uniquePlans, ArrayList<Sede> uniqueSede, Scanner scanner) throws ParseException {
        int choice = 0;
        String[] choices = {
            "1) Agregar cliente",
            "2) Eliminar cliente",
            "3) Editar cliente",
            "4) Buscar cliente",
            "5) Ordenar clientes alfabeticamente",
            "6) Lista clientes",
            "7) Atrás"
        };

        do {
            try {
                Menu.printMenu("¿Qué operación desea ejecutar?", choices);
                choice = Integer.parseInt(scanner.nextLine().trim());
                if (choice == 1) {
                    System.out.println("A continuación, se agregará un cliente...");
                    Utils.parseCliente(Utils.agregarCliente(data, uniquePlans, uniqueSede, scanner), data);
                    System.out.println("Cliente agregado exitosamente.");
                    
                    Main.FlagChanges = true;
                    pressEnter();
                } else if (choice == 2) {
                    System.out.println("------------------------------------------------");
                    System.out.println("A continuación, se eliminará a un cliente de la lista...");
                    int index = Menu.queryClient(scanner, data);
                    if (index != 0) {
                        data.remove(index);
                        System.out.println("El cliente ingresado se ha borrado exitosamente");
                        Main.FlagChanges = true;
                        pressEnter();
                    }
                } else if (choice == 3) {
                    System.out.println("------------------------------------------------");
                    System.out.println("A continuación, se editará un cliente de la lista...");
                    int index = queryClient(scanner, data);
                    if (index != 0) {
                        Utils.editCliente(index, uniquePlans, uniqueSede, data, scanner);
                        Main.FlagChanges = true;
                        pressEnter();
                    }
                } else if (choice == 4) {
                    System.out.println("------------------------------------------------");
                    String[] choices2 = {
                        "1) Nombre (todas las ocurrencias)",
                        "2) Rut"
                    };
                    Menu.printMenu("¿Cómo desea buscar al cliente?", choices2);
                    try {
                        choice = Integer.parseInt(scanner.nextLine().trim());
                        if (choice == 1) {
                            String nombre;
                            System.out.print("Ingrese el nombre: ");
                            nombre = scanner.nextLine();
                            int resultados[] = Utils.buscarNombre(nombre, data);
                            System.out.println("\n--------------------------------");
                            System.out.println("Resultados para la búsqueda:");
                            System.out.println("--------------------------------\n");
                            for (int i : resultados) {
                                if (i == -1) {continue;}
                                System.out.println(Arrays.toString(data.get(i).getData()));
                            } 
                        } else if (choice == 2) {
                            String rut;
                            System.out.print("Ingrese el rut: ");
                            rut = scanner.nextLine();
                            int index = Utils.buscarRut(rut, data);
                            System.out.println("\n--------------------------------");
                            System.out.println("Resultados para la búsqueda:");
                            System.out.println("--------------------------------\n");
                            if (index != -1) {
                                System.out.println(Arrays.toString(data.get(index).getData()));
                            }
                        }
                    } catch (NumberFormatException e) {
                        System.out.print("Entrada inválida. ");
                    }
                    pressEnter();
                } else if (choice == 5) {
                    // ...
                    System.out.println("------------------------------------------------");
                    Utils.orderClientes(data);
                    Main.FlagChanges = true;
                    System.out.println("Lista de clientes ordenada existosamente");
                    pressEnter();
                } else if (choice == 6) {
                    System.out.println("------------------------------------------------");
                    System.out.println("A continuación, se mostrará lista de clientes...");
                    for (int i=0; i<data.size(); i++) {
                        System.out.println(Arrays.toString(data.get(i).getData()));
                    }
                    pressEnter();
                } else if (choice == 7) {break;}
                else {
                    System.out.print("Opción inválida. ");
                    pressEnter();
                }
            } 
            catch (NumberFormatException e) {
                System.out.print("Entrada inválida. ");
                pressEnter();
            }
        } while (choice != 7);
    }

    
    public static void menuPlanes(ArrayList<Client> data, ArrayList<Plan> uniquePlans, ArrayList<Sede> uniqueSede, Scanner scanner) throws ParseException {
        System.out.print("\033\143");
        int choice = 0;
        String[] choices = {
            "1) Agregar plan",
            "2) Quitar sede",
            "3) Cambiar plan (cliente)",
            "4) Lista planes",
            "5) Atrás"
        };
        do {
            try {
                Menu.printMenu("¿Qué operación desea ejecutar?", choices);
                choice = Integer.parseInt(scanner.nextLine().trim());
                if (choice == 1) {
                    Utils.addPlan(uniquePlans, scanner);
                    Main.FlagChanges = true;
                    pressEnter();
                } else if (choice == 2) {
                    Utils.delPlan(uniquePlans, data, scanner);
                    Main.FlagChanges = true;
                    pressEnter();
                } else if (choice == 3) {
                    int index = queryClient(scanner, data);
                    if (index != 0) {
                        String[] opcion = new String[] {Integer.toString(4), Integer.toString(index)};
                        Utils.parseCliente(Utils.agregarCliente(data, uniquePlans, uniqueSede, scanner, opcion), data);
                        data.remove(index);
                        Main.FlagChanges = true;
                        System.out.println("Plan cambiado exitosamente");
                        pressEnter();
                    }
                } else if (choice == 4) {
                    System.out.println("A continuación, se mostrará la lista de sedes...");
                    for (int i=0; i<uniquePlans.size(); i++) {
                        System.out.println(uniquePlans.get(i).codPlan);
                        System.out.println(uniquePlans.get(i).descPlan + "\n");
                    }
                    pressEnter();
                } else if (choice == 5) {continue;}
                else {
                    System.out.print("Opción inválida. ");
                }
            } 
            catch (NumberFormatException e) {
                System.out.print("Entrada inválida. ");
                pressEnter();
            }
        } while (choice != 5);
    }

    public static void menuSedes(ArrayList<Client> data, ArrayList<Plan> uniquePlans, ArrayList<Sede> uniqueSede, Scanner scanner) throws ParseException {
        System.out.print("\033\143");
        int choice = 0;
        String[] choices = {
            "1) Agregar sede",
            "2) Quitar sede",
            "3) Cambiar sede (cliente)",
            "4) Lista sedes",
            "5) Atrás"
        };
        do {
            try {
                Menu.printMenu("¿Qué operación desea ejecutar?", choices);
                choice = Integer.parseInt(scanner.nextLine().trim());
                if (choice == 1) {
                    Utils.addSede(uniqueSede, scanner);
                    Main.FlagChanges = true;
                    pressEnter();
                } else if (choice == 2) {
                    Utils.delSede(uniqueSede, data, scanner);
                    Main.FlagChanges = true;
                    pressEnter();
                } else if (choice == 3) {
                    int index = queryClient(scanner, data);
                    if (index != 0) {
                        String[] opcion = new String[] {Integer.toString(5), Integer.toString(index)};
                        Utils.parseCliente(Utils.agregarCliente(data, uniquePlans, uniqueSede, scanner, opcion), data);
                        data.remove(index);
                        Main.FlagChanges = true;
                        System.out.println("Sede cambiada exitosamente");
                        pressEnter();
                    }
                } else if (choice == 4) {
                    System.out.println("A continuación, se mostrará la lista de sedes...");
                    for (int i=0; i<uniqueSede.size(); i++) {
                        System.out.println(uniqueSede.get(i).codSede);
                        System.out.println(uniqueSede.get(i).ubicSede + "\n");
                    }
                    pressEnter();
                } else if (choice == 5) {continue;}
                else {
                    System.out.print("Opción inválida. ");
                }
            } 
            catch (NumberFormatException e) {
                System.out.print("Entrada inválida. ");
                pressEnter();
            }
        } while (choice != 5);
    }

    public static int queryClient(Scanner scanner, ArrayList<Client> data) {
        int choice = 0;
        String[] choices = {
            "1) Nombre",
            "2) Rut",
            "3) Atrás"
        };

        int index = -1;
        do {
            try {
                printMenu("¿Cómo desea buscar al cliente?", choices);
                choice = Integer.parseInt(scanner.nextLine().trim());
                if (choice == 1) {
                    String nombre;
                    System.out.print("Ingrese el nombre: ");
                    nombre = scanner.nextLine();
                    for (int i : Utils.buscarNombre(nombre, data)) {
                        if (i == -1) {continue;}
                        index = i;
                    }
                } else if (choice == 2) {
                    String rut;
                    System.out.print("Ingrese el rut: ");
                    rut = scanner.nextLine();
                    index = Utils.buscarRut(rut, data);
                } else if (choice == 3) {
                    index = 0;
                } else {
                    System.out.print("Opción inválida. ");
                    pressEnter();
                }
            } catch (NumberFormatException e) {
                System.out.print("Entrada inválida. ");
                pressEnter();
            }
            if ((choice == 1 || choice == 2) && (index == -1)) {
                System.out.println("Usuario no encontrado. Intente de nuevo");
                pressEnter();
            }
        } while (index == -1);
        return index;
    }

    public static void printMenu(String prompt, String[] choices) {
        System.out.print("\033\143");
        System.out.println("--------------------------------");
        System.out.println(prompt);
        System.out.println("--------------------------------");
        for (String choice : choices) {
            System.out.println(choice);
        }
        System.out.print("Ingresar una opción: ");
    }

    public static void pressEnter() {
        System.out.println("\n------------------------------------------");
        System.out.println("Presione 'enter' para continuar.");
        System.out.println("------------------------------------------");
        while (true) {
            int c;
            try {
                c = System.in.read();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            if (c == -1 || c == '\n') return;
        }
    }
}