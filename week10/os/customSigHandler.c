#include <stdio.h>
#include <stdlib.h>
#include <signal.h>
#include <unistd.h>

volatile sig_atomic_t signal_count = 0;

void handle_sigint(int sig) {
	const char message[] = "SIGINT received. Press Ctrl+C two more times to exit.\n";

	(void)sig;
	signal_count++;

	if (signal_count < 3) {
		write(STDOUT_FILENO, message, sizeof(message) - 1);
	} else {
		const char exit_message[] = "SIGINT received 3 times. Exiting.\n";
		write(STDOUT_FILENO, exit_message, sizeof(exit_message) - 1);
		_exit(EXIT_SUCCESS);
	}
}

int main(void) {
	if (signal(SIGINT, handle_sigint) == SIG_ERR) {
		perror("signal");
		return EXIT_FAILURE;
	}

	printf("Program running (PID: %d). Try pressing Ctrl+C...\n", getpid());

	while (1) {
		sleep(1);
	}

	return 0;
}
