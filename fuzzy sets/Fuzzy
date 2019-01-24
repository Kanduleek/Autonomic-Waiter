#!/usr/bin/env python

def quality_good(x):
	if x <= 4:
		return 0
	elif x > 4:
		return 1 - (10 - float(x))/6

def quality_bad(x):
	if x >= 6:
		return 0
	elif x < 6:
		return 1 - (abs(0 - float(x)))/6

def quality_average(x):
	if x <= 2.5 or x >= 7.5:
		return 0
	elif 2.5 < x and x < 7.5:
		return 1 - (float(abs(5-x))/2.5)

def tip_low(x):
	if x >= 10:
		return 0
	elif x < 10:
		return 1 - (float(x)/10)

def tip_high(x):
	if x <= 10:
		return 0
	elif x > 10:
		return (float(x)/10) -1

def tip_average(x):
	if x <= 5 or x >= 15:
		return 0
	elif 5 < x < 15:
		return 1 - (float(abs(10-x))/5.0)


if __name__ == "__main__":
	food = float(raw_input("Podaj ocene jedzenia:"))
	service = float(raw_input("Podaj ocene obslugi:"))
	high_score = max(quality_good(food), quality_good(service))

	low_score = min(quality_bad(food), quality_bad(service))

	average_score = quality_average(service)

	n = 0
	d = 0
	for x in xrange(0,20, 1):
		if (high_score == 1):
			n += 20 * high_score
			d += high_score
		elif (low_score == 1):
			n += 0 * low_score
			d += low_score
		else:
			degree =  max(min(tip_low(x), low_score), min(tip_average(x), average_score), min(tip_high(x), high_score))
			n += x * degree
			d += degree

	if float(d)==0:
		print "Nie mozna dzielic przez 0!"
	else:
		output = float(n)/float(d)
if food > 10 or service > 10:
	print "Liczba musi miescic sie w przedziale 0-10"
else:
	print "Napiwek wynosi: %.2f procent" % output
