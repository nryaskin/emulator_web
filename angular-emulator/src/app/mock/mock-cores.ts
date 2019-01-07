import { Core } from '../cores/shared/core.model';
import { CHIP_INPUT_KEYS, GAMEBOY_INPUT_KEYS, TUTTURU_INPUT_KEYS } from './mock-inputs'

export const CORES: Core[] = [
  { id: 11, name: 'Tutturu', path: 'home/tutturu', keys: TUTTURU_INPUT_KEYS },
  { id: 12, name: 'Chip-8', path: 'home/chip-8', keys: CHIP_INPUT_KEYS },
  { id: 13, name: 'GameBoy', path: 'home/gameboy', keys: GAMEBOY_INPUT_KEYS }
];
