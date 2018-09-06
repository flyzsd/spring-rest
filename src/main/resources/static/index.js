'use strict';

//https://github.com/ReactiveX/rxjs/blob/master/src/internal/observable/dom/AjaxObservable.ts
const {Observable, Subject, ReplaySubject, from, of, range} = rxjs;
const {map, filter, switchMap, catchError} = rxjs.operators;
const {ajax} = rxjs.ajax;

ajax.getJSON(`https://api.github.com/users?per_page=5`)
    .pipe(
        map(userResponse => console.log('users: ', userResponse)),
        catchError(error => console.log('error: ', error))
    ).subscribe(e => {
    console.log(e);
});

ajax.get(`https://api.github.com/users?per_page=5`)
    .pipe(
        map(userResponse => console.log('users: ', userResponse)),
        catchError(error => console.log('error: ', error))
    ).subscribe(e => {
    console.log(e);
});