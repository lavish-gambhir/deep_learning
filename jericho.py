# lge codejam --> question 2
def main():
    input_items = [int(item) for item in input().split()]
    input_list = [int(item) for item in input().split()]
    input_list[input_items[1] - 1] = -211
    item_to_find = input_list[input_items[1] - 1]
    for i in range(input_items[2]):
        op_to_perform = int(input())
        if op_to_perform > 0:
            input_list = input_list[op_to_perform-1::-1] + \
                        input_list[op_to_perform:]
        elif op_to_perform < 0:
            final_op_to_perform = len(input_list) + op_to_perform
            new_update = input_list[final_op_to_perform::]
            new_update = new_update[::-1]
            input_list = input_list[:final_op_to_perform] + new_update
    print(input_list.index(item_to_find) + 1)
if __name__ == '__main__':
    main()
