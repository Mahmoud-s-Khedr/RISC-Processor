addi r1, r0, 0			# r1 = a = 0
addi r2, r0, 7			# r2 = b = 7
addi r3, r0, 10			# r3 = 10

loop:
	bge r1, r3, exit	# if a >= 10 exit
	add r4, r1, r2		# a + b
	sw r4, 0(r1)		# mem[a] = a + b
	addi r1, r1, 1		# a += 1
	j loop			# repeat

exit:
	j exit