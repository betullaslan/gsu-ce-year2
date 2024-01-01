# Counting Sort Implementation in C

This project presents a basic implementation of the **Counting Sort** algorithm in the C programming language.  
The algorithm efficiently sorts an array of non-negative integers where the maximum possible value is known in advance.

---

## Project Info

- **Project Name:** Counting Sort in C  
- **Language:** C  
- **Author:** Betul Aslan  

---

## Academic Info

- **Course:** Algorithms And Data Structures 
- **Institution:** Galatasaray University  
- **Department:** Computer Engineering  
- **Academic Year:** 2023–2024 Fall 
- **Assignment:** Term Project

---

## Topics Covered

- Counting Sort algorithm  
- Static arrays and fixed memory usage  
- Array traversal and frequency counting  
- Cumulative frequency computation  
- Sorting based on stable indexing  

---

## How It Works

1. **Initialization**: Determine the maximum value in the array.  
2. **Counting Phase**: Count occurrences of each number using a helper array.  
3. **Cumulative Phase**: Transform the counts into cumulative counts.  
4. **Sorting Phase**: Place elements into their correct positions in a result array.  
5. **Final Step**: Copy the sorted result back into the original array.

---

## Compilation and Run

You can compile and run the program with a C compiler like `gcc`:

```bash
gcc counting_sort.c -o counting_sort
./counting_sort
