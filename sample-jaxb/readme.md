
# XML Schema Data Types

See: https://www.w3.org/TR/xmlschema-2/

## dateTime

Format:

- `'-'? yyyy '-' mm '-' dd 'T' hh ':' mm ':' ss ('.' s+)? (zzzzzz)?`
- `'-'? yyyy` is a four-or-more digit optionally negative-signed numeral,
  if more than four digits, leading zeros are prohibited, and '0000' is prohibited
- `hh` permits '24' if the minutes and seconds represented are zero
- `zzzzzz` (if present) represents the timezone of the form `(('+' | '-') hh ':' mm) | 'Z'`

>  '0001' is the lexical representation of the year 1 of the Common Era (1 CE, sometimes written "AD 1" or "1 AD").
> There is no year 0, and '0000' is not a valid lexical representation.
> '-0001' is the lexical representation of the year 1 Before Common Era (1 BCE, sometimes written "1 BC")
