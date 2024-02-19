Задание:

1. На базе первого примера разобранного на семинаре, добавить в один из проектов разработанных ранее spring Integration. Сохранять запросы от пользователя в файл.
* добавлен класс FileGateway.java
* добавлен класс IntegrationConfig.java
* внесены изменения в метод @PostMapping("/add")
    public String addTask(@ModelAttribute("task") Task task)
  в контроллере TaskWebController.java

2. Добавить в проект один из паттернов разобранных на лекции.
* попыталась отразить применение Strategy для выбора типа сортировки задач
* добавлен пакет strategy, содержащий интерфейс TaskSortingStrategy.java и классы Стратегий сортировки задач, каждый по своей логике.
* добавлен сервисный класс для работы с сортировкой TaskSortingService.java
* в контроллере TaskWebController.java добавлен метод для сортировки @GetMapping("/sorted")
    public String getSortedTasks(@RequestParam(required = false) String sortBy,
                                 Model model),
  который принимает в параметре ключ для сортировки и передает его в сервис для выбора конкретной стратегии.
* обновлен шаблон представления taskList.html  
