#include <stdio.h>
#include <stdlib.h>

#define MAX_SIZE 3

typedef struct{
    int a[MAX_SIZE];
    int top;
} Stack;


void initialize(Stack *s) {
    s-> top= -1;
}


int is_empty(Stack *s) {
    return s-> top == -1;
}

int size(Stack *s) {
    return s-> top + 1;
}

void push(Stack *s, int key) {
    if(size(s) == MAX_SIZE) {
        printf("Overflow");
        return;
    }
    s->a[++s-> top]= key;
}

int pop(Stack *s) {
    if(is_empty(s)) {
        printf("Underflow");
        return -1;
    }
    return s->a[s->top--];
}

int main() {

    Stack s;

    // Initialize stack
    initialize(&s);

    // Push elements
    push(&s, 10);
    push(&s, 20);
    push(&s, 30);

    printf("Size: %d\n", size(&s));

    // Try pushing when full
    push(&s, 40);

    // Pop elements
    printf("Popped: %d\n", pop(&s));
    printf("Popped: %d\n", pop(&s));

    printf("Size: %d\n", size(&s));

    printf("Popped: %d\n", pop(&s));

    // Try popping when empty
    printf("Popped: %d\n", pop(&s));

    return 0;
}