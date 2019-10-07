Menu menu = new Menu();
menu.add("Burger","Hamburger",399)
menu.add("Snack","Fries",189)
menu.add("Combo","combo2", Arrays.asList(1, 0))
menu.add("Drink","SoftDrink",149)
menu.add("Combo","HappyMeal", Arrays.asList(0, 1, 2))
menu.add("Snack","Drumlets",169)
menu.add("Combo","combo6", Arrays.asList(0, 1, 2, 2, 1, 0))
menu.add("Burger","CheeseBurger",200)
menu.add("Combo","combo0", Arrays.asList(0, 0, 0, 0, 0, 0))
menu.add("Drink","OrangeJuice",209)
menu.print()
new Order(menu).add(new int[]{2, 1, 4, 3})
/exit
