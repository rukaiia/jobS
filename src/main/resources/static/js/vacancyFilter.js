document.addEventListener("DOMContentLoaded", function() {
    const savedFilter = localStorage.getItem("vacancyFilter");
    const dropdownItems = document.querySelectorAll(".dropdown-item");
    let currentFilter = savedFilter;

    if (savedFilter) {
        applyFilter(savedFilter);
    }

    dropdownItems.forEach(item => {
        item.addEventListener("click", function(event) {
            event.preventDefault();
            const filter = event.target.getAttribute("href").split("=")[1];

            localStorage.setItem("vacancyFilter", filter);

            if (filter !== currentFilter) {
                applyFilter(filter);
            }
        });
    });

    function applyFilter(filter) {
        console.log("Применен фильтр: " + filter);
        currentFilter = filter;

        if (window.location.search !== "?filter=" + filter) {
            window.location.href = "?filter=" + filter;
        }
    }
});