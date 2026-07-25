/*
man 3 stat ---> this gives the C library functions (3 --> C library functions)
    st_dev This  field  describes  the  device  on which this file resides.
              (The major(3) and minor(3) macros may be useful to decompose the
              device ID in this field.)

       st_ino This field contains the file's inode number.

       st_mode
              This field contains the file type and mode.   See  inode(7)  for
              further information.

       st_nlink
              This field contains the number of hard links to the file.

       st_uid This field contains the user ID of the owner of the file.

       st_gid This field contains the ID of the group owner of the file.

       st_rdev
              This  field  describes  the device that this file (inode) repre‐
              sents.

       st_size
              This field gives the size of the file (if it is a  regular  file
              or  a  symbolic  link) in bytes.  The size of a symbolic link is
              the length of the pathname it contains,  without  a  terminating
              null byte.

        st_blksize
              This  field  gives  the  "preferred"  block  size  for efficient
              filesystem I/O.

       st_blocks
              This field indicates the number of blocks allocated to the file,
              in 512-byte units.  (This may be smaller than  st_size/512  when
              the file has holes.)

       st_atime
              This is the time of the last access of file data.

       st_mtime
              This is the time of last modification of file data.

       st_ctime
              This  is  the  file's last status change timestamp (time of last
              change to the inode).

*/

#include <stdio.h>
#include <stdlib.h>
#include <sys/stat.h>
#include <time.h>

// for permission denied test case just change the permission of testdir to 000 then run the program for secret.txt which is inside testdir, it will give permission denied error
int main(int argc, char *argv[]) {
    struct stat fileStat;

    // filepath is provided or not
    if(argc != 2) {
        printf("Usage: %s <file_path>\n", argv[0]);
        return 1;
    }

    //file info
    if(stat(argv[1], &fileStat) == -1) { //0= success, -1=error
        perror("stat");
        return 1;
    }

    // file type and mode
    if (S_ISREG(fileStat.st_mode)) { // contains file type and mode, S_ISREG is a macro to check if it's a regular file
        printf("File Type: Regular File\n");
    }
    else if (S_ISDIR(fileStat.st_mode)) {
        printf("File Type: Directory\n");
    }
    else {
        printf("File Type: Other\n");
    }

    // File Permissions
    printf("Permissions:          %04o\n", fileStat.st_mode & 0777); // everything except where is 1 becomes 0, 0777 is octal for rwxrwxrwx (min widdth 4 digits) fill unused with 0s

    printf("File Size:            %ld bytes\n", fileStat.st_size);

    // Number of Hard Links
    printf("Hard Links Count:     %ld\n", fileStat.st_nlink);

    // Owner UID and GID
    printf("Owner (UID):          %d\n", fileStat.st_uid);
    printf("Group (GID):          %d\n", fileStat.st_gid);

    // Access, Modification and Status Change Time
    printf("Last Access Time:     %s", ctime(&fileStat.st_atime));
    printf("Last Modification:    %s", ctime(&fileStat.st_mtime));
    printf("Status Change Time:   %s", ctime(&fileStat.st_ctime));

    return 0;

}

