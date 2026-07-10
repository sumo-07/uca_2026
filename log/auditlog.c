#include <stdio.h>
#include <string.h>
#include <fcntl.h>
#include <unistd.h>

#define LOGFILE "audit.log"
#define BUFFER_SIZE 1024

int main(int argc, char *argv[]) {

    if (argc < 2) {
        printf("Usage:\n");
        printf("./auditlog --add \"message\"\n");
        printf("./auditlog --view\n");
        return 1;
    }

    // ---------------- ADD ----------------
    if (strcmp(argv[1], "--add") == 0) {

        if (argc != 3) {
            printf("Usage: ./auditlog --add \"message\"\n");
            return 1;
        }

        int fd = open(LOGFILE, O_WRONLY | O_CREAT | O_APPEND, 0644);

        if (fd < 0) {
            perror("Error opening file");
            return 1;
        }

        write(fd, argv[2], strlen(argv[2]));
        write(fd, "\n", 1);

        close(fd);
    }

    else if (strcmp(argv[1], "--view") == 0) {

        int fd = open(LOGFILE, O_RDONLY);

        if (fd < 0) {
            write(1, "No log file found.\n", 19);
            return 1;
        }

        char buffer[BUFFER_SIZE];
        int bytesRead;
        int line = 1;

        char num[20];

        sprintf(num, "%d: ", line++);
        write(1, num, strlen(num));

        while ((bytesRead = read(fd, buffer, BUFFER_SIZE)) > 0) {

            for (int i = 0; i < bytesRead; i++) {

                write(1, &buffer[i], 1);

                if (buffer[i] == '\n' && i != bytesRead - 1) {
                    sprintf(num, "%d: ", line++);
                    write(1, num, strlen(num));
                }
            }
        }

        close(fd);
    }
    else {
        printf("Invalid command.\n");
        printf("./auditlog --add \"message\"\n");
        printf("./auditlog --view\n");
    }

    return 0;
}