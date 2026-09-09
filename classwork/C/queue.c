#include <stdio.h>
#include <stdlib.h>

#define MAX_SIZE 3

typedef struct{
    int a[MAX_SIZE];
    int front;
    int rear;
    int size;
} Stack;


void initialize(Stack *s) {
    s->front= 0;
    s->rear= 0;
    s->size= 0;
}


int is_empty(Stack *s) {
    return s->size == 0;
}

int size(Stack *s) {
    return s->size;
}

void push(Stack *s, int key) {
    printf("Inserting%d\n", key);
    if(size(s) == MAX_SIZE) {
        printf("Overflow");
        return;
    }
    s->a[s->rear]= key;
    s->rear= (s->rear + 1) % MAX_SIZE;
    s->size++;
    
}

int pop(Stack *s) {
    if(is_empty(s)) {
        printf("Underflow");
        return -1;
    }

    int result= s->a[s->front];
    s->front= (s->front + 1)%MAX_SIZE;
    s->size--;
    return result;

}

int main() {

    Stack stack;

    initialize(&stack);

    push(&stack, 10);
    push(&stack, 20);
    push(&stack, 30);

    printf("Size: %d\n", size(&stack));

    printf("Removed: %d\n", pop(&stack));
    printf("Removed: %d\n", pop(&stack));

    push(&stack, 40);
    push(&stack, 50);

    printf("Removed: %d\n", pop(&stack));
    printf("Removed: %d\n", pop(&stack));
    printf("Removed: %d\n", pop(&stack));

    // Try popping from empty queue
    printf("Removed: %d\n", pop(&stack));

    return 0;
}