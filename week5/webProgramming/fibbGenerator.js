function* fibonacci() {
    let prev = 0;
    let curr = 1;
    yield prev;
    yield curr;
    while (true) {
        let nextVal = prev + curr;
        yield nextVal;
        prev = curr;
        curr = nextVal;
    }
}


const fibGen = fibonacci();

console.log(fibGen.next().value);
console.log(fibGen.next().value);
console.log(fibGen.next().value);
console.log(fibGen.next().value);
console.log(fibGen.next().value);
console.log(fibGen.next().value);
console.log(fibGen.next().value);
console.log(fibGen.next().value);
console.log(fibGen.next().value);
