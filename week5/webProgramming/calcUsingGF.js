function* calc(initialValue) {
    let result = initialValue;
    let yieldValue = result;

    while (true) {
        const input = yield yieldValue; // return curr result/error and take args from the .next(args)

        try {
            if (
                !input ||
                !["add", "subtract", "multiply", "divide"].includes(input.operation) ||
                typeof input.value !== "number" ||
                Number.isNaN(input.value)
            ) {
                yieldValue = "Invalid input";
                continue;
            }

            switch (input.operation) {
                case "add":
                    result += input.value;
                    break;

                case "subtract":
                    result -= input.value;
                    break;

                case "multiply":
                    result *= input.value;
                    break;

                case "divide":
                    if (input.value === 0) {
                        yieldValue = "Cannot divide by zero";
                        continue;
                    }
                    result /= input.value;
                    break;
            }

            yieldValue = result;
        } catch (error) {
            yieldValue = "Invalid input";
        }
    }
}


const c = calc(50);

console.log(c.next());
// { value: 50, done: false }

console.log(c.next({ operation: "add", value: 30 }));
// { value: 80, done: false }

console.log(c.next({ operation: "multiply", value: 2 }));
// { value: 160, done: false }

console.log(c.next({ operation: "add", value: "30" }));
// { value: "Invalid input", done: false }

console.log(c.next({ operation: "multiply", value: 0 }));
// { value: 0, done: false }

