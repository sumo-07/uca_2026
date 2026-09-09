function NewPromise(executorFunction) {
    let state = "pending";
    let value;
    let error;

    let thenCallbacks = [];
    let catchCallbacks = [];

    function resolve(data) {
        if (state !== "pending")
            return;

        state = "fulfilled";
        value = data;

        thenCallbacks.forEach(callback => {
            callback(value);
        });
    }

    function reject(err) {
        if (state !== "pending")
            return;

        state = "rejected";
        error = err;

        catchCallbacks.forEach(callback => {
            callback(error);
        });
    }

    this.then = function(callback) {

        return new NewPromise((resolve, reject) => {

            function handle(value) {
                try {
                    let result = callback(value);

                    resolve(result);
                }
                catch (err) {
                    reject(err);
                }
            }

            if (state === "fulfilled") {
                handle(value);
            }
            else if (state === "pending") {
                thenCallbacks.push(handle);
            }
        });
    };

    this.catch = function(callback) {

        return new NewPromise((resolve, reject) => {

            function handle(error) {
                try {
                    let result = callback(error);

                    resolve(result);
                }
                catch (err) {
                    reject(err);
                }
            }

            if (state === "rejected") {
                handle(error);
            }
            else if (state === "pending") {
                catchCallbacks.push(handle);
            }
        });
    };

    try {
        executorFunction(resolve, reject);
    }
    catch (err) {
        reject(err);
    }
}

new NewPromise(resolve => {
    resolve(5);
})
.then(value => {
    return value * 2;
})
.then(value => {
    return value * 5;
})
.then(value => {
    console.log(value);
});