# Calculate the Poduct of an array
# Inputs:
# $1 contains the address of the first element
# $2 contains the length
# The output is stored in $5


# inputs
addi $1, $0, 5 
addi $2, $0, 6


addi $3, $0, 0
# Loop over length - 1
addi $2, $2, -1

product_loop:
beq $3, $2, exit

add $4, $1, $3

addi $5, $4, 0
jal mul

sw $5, 1($4)
addi $3, $3, 1

j product_loop

exit: j exit



mul:

# Set the stack pointer at the last position in the memory
addi $6, $0, -1

# Save Preserved values
sw $4, 0($6) 
sw $3, -1($6)
sw $2, -2($6)

# Load inputs
lw $4, 0($5) 
lw $3, 1($5)

addi $2, $0, 0
addi $5, $0, 0

loop:
beq $2, $4, return
add $5, $5, $3
addi $2, $2, 1
j loop

return:

# Load Preserved values
lw $4, 0($6)
lw $3, -1($6)
lw $2, -2($6)

jr $7
