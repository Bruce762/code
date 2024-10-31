char* a(){
    char seg[7]="000000";
    return &seg[0];
}
int main(int argc, char *argv[]) {
    printf("%s",a());
}