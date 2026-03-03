from collections import defaultdict
import re

PATTERN = re.compile(r'(.+?), (.+?), (.+?(?= [A-Z]{2})) ([A-Z]{2})')
STATES = {"CA": "California",
          "MA": "Massachusetts",
          "OK": "Oklahoma",
          "PA": "Pennsylvania",
          "VA": "Virginia",
          "AZ": "Arizona",
          "ID": "Idaho",
          "IN": "Indiana"}

def by_state(s):
    dct = defaultdict(list)
    for name,addrs,city,state in PATTERN.findall(s):
        dct[STATES[state]].append("..... {} {} {} {}".format(name, addrs, city, STATES[state]))

    return '\r\n '.join( "{}\r\n{}".format(state, '\r\n'.join(sorted(lst))) for state,lst in sorted(dct.items()) )