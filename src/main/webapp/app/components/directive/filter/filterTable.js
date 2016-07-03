angular.module('stApp.filterTable', [])
    .component('filterTable', {
        templateUrl: 'app/components/directive/filter/filterTable.html',
        controller: FilterTableCtrl,
        bindings: {
            searchParams: "="
        }
    });

function FilterTableCtrl(){
    console.log(this.searchParams);
}