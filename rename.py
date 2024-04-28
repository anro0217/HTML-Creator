import os

os.chdir("path to Pictures root directory")

print(os.getcwd())

for count, f in enumerate(os.listdir(), start=1):

    f_name, f_ext = os.path.splitext(f)

    f_name = "new name of the pictures" + str(count)

    new_name = f"{f_name}{f_ext}"

    os.rename(f, new_name)
